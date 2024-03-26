package com.heyqing.shop_back.service.impl.admin;

import com.heyqing.shop_back.constant.OSSTypeConstant;
import com.heyqing.shop_back.dao.entity.ChoiceAvatarEntity;
import com.heyqing.shop_back.dao.mapper.ChoiceAvatarMapper;
import com.heyqing.shop_back.dao.pojo.AdminChoiceAvatarSavePO;
import com.heyqing.shop_back.dao.pojo.enums.MateEnum;
import com.heyqing.shop_back.service.admin.AdminChoiceAvatarService;
import com.heyqing.shop_back.utils.OSSUtil;
import com.heyqing.shop_back.vo.Mate;
import com.heyqing.shop_back.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * ClassName:AdminChoiceAvatarServiceImpl
 * Package:com.heyqing.shop_back.service.impl.admin
 * Description:
 *
 * @Date:2024/3/10
 * @Author:Heyqing
 */
@Service
public class AdminChoiceAvatarServiceImpl implements AdminChoiceAvatarService {

    @Autowired
    private ChoiceAvatarMapper choiceAvatarMapper;

    private static final Long CHOICE_AVATAR_URL_ID = 2024031017L;
    private static final String  AVATAR_TYPE = "theme";

    private final OSSUtil aliyunOSSUtil;

    @Autowired
    public AdminChoiceAvatarServiceImpl(OSSUtil aliyunOSSUtil) {
        this.aliyunOSSUtil = aliyunOSSUtil;
    }

    @Override
    public Result getUrl() {
        ChoiceAvatarEntity avatarEntity = choiceAvatarMapper.selectById(CHOICE_AVATAR_URL_ID);
        Map<String,String> map= new HashMap<>();
        if (Objects.isNull(avatarEntity)){
            //不存在
            return Result.fail(new Mate(500,"出错了，请联系管理员"));
        }else {
            String themeAvatar = avatarEntity.getChoiceAvatar();
            String detailAvatar = avatarEntity.getChoicePhoto();
            map.put("themeAvatar",themeAvatar);
            map.put("detailAvatar",detailAvatar);
        }
        return Result.success(map,new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    @Override
    public Result uploadImage(MultipartFile file) {
        String uploadUrl = upload(file,  OSSTypeConstant.CHOICE_AVATAR_PATH);
        if (uploadUrl == null){
            return Result.fail(new Mate(MateEnum.FAIL.getCode(), MateEnum.FAIL.getMsg()));
        }
        return Result.success(uploadUrl,new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    @Override
    public Result save(AdminChoiceAvatarSavePO adminChoiceAvatarSavePO) {
        if (Objects.isNull(adminChoiceAvatarSavePO)){
            return Result.fail(new Mate(MateEnum.FAIL.getCode(), MateEnum.FAIL.getMsg()));
        }
        ChoiceAvatarEntity avatarEntity = choiceAvatarMapper.selectById(CHOICE_AVATAR_URL_ID);
        if (Objects.isNull(avatarEntity)){
            avatarEntity = new ChoiceAvatarEntity();
            avatarEntity.setChoiceAvatarId(CHOICE_AVATAR_URL_ID);
            choiceAvatarMapper.insert(avatarEntity);
        }
        try {
            if (adminChoiceAvatarSavePO.getType().equals(AVATAR_TYPE)){
                //主题
                avatarEntity.setChoiceAvatar(adminChoiceAvatarSavePO.getImgUrl());
            }else {
                avatarEntity.setChoicePhoto(adminChoiceAvatarSavePO.getImgUrl());
            }
            choiceAvatarMapper.updateById(avatarEntity);
            return Result.success(null,new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
        }catch (Exception e){
            return Result.fail(new Mate(MateEnum.FAIL.getCode(), MateEnum.FAIL.getMsg()));
        }
    }

    /*******************************************************private***************************************************/


    private String upload(MultipartFile file,  String typePath) {
        // 返回客户端文件系统中的原始文件名
        String fileName = file.getOriginalFilename();
        try {
            if (file != null) {
                // 判定文件名是否为 ""
                if (!"".equals(fileName.trim())) {
                    File newFile = new File(fileName);
                    FileOutputStream os = new FileOutputStream(newFile);
                    // 以字节数组的形式返回文件的内容,再输出到文件输出流中
                    os.write(file.getBytes());
                    os.close();
                    // 将接受的文件传输到给定的目标文件 file-->newFile
                    file.transferTo(newFile);
                    String timeStamp = String.valueOf(System.currentTimeMillis());
                    fileName = timeStamp + fileName.substring(fileName.lastIndexOf("."));
                    String path;
                    if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")) {
                        // images
                        path = "images/" + typePath + fileName;
                    } else if (fileName.endsWith(".mp4")) {
                        path = "video/" + typePath + fileName;
                    } else {
                        return null;
                    }
                    // 上传到OSS
                    String uploadUrl = aliyunOSSUtil.upLoad(newFile, path);
                    newFile.delete();
                    if (uploadUrl != null) {
                        return uploadUrl;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
