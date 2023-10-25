package com.enicom.board.kyoritsu.api.service.image;

import com.enicom.board.kyoritsu.api.param.IntroductionsParam;
import com.enicom.board.kyoritsu.api.param.file.FileInfoParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleType;
import com.enicom.board.kyoritsu.api.service.introductions.IntroductionsService;
import com.enicom.board.kyoritsu.api.type.PageVO;
import com.enicom.board.kyoritsu.api.type.ResponseDataValue;
import com.enicom.board.kyoritsu.dao.entity.Content;
import com.enicom.board.kyoritsu.dao.entity.Image;
import com.enicom.board.kyoritsu.dao.repository.image.ImageRepository;
import com.enicom.board.kyoritsu.dao.repository.introduction.IntroductionRepository;
import com.enicom.board.kyoritsu.login.MemberDetail;
import com.enicom.board.kyoritsu.login.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.enicom.board.kyoritsu.api.service.file.FileService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    private static final String ImagePath = "Image";
    private final SecurityUtil securityUtil;
    private final ImageRepository imageRepository;


    @Autowired
    public ImageServiceImpl(SecurityUtil securityUtil, ImageRepository imageRepository) {
        this.securityUtil = securityUtil;
        this.imageRepository = imageRepository;
    }

    @Override
    public ResponseDataValue<String> upload(MultipartHttpServletRequest request, String name, MultipartFile file) {
        System.out.println("key값" + name);
        System.out.println("file값" + file);
        ResponseDataValue<String> result = FileService.upload(ImagePath, file, name);
        System.out.println(result);
        System.out.println(result.getResult());
        System.out.println(result.getCode());
        System.out.println(name);
        System.out.println(file);

        if (result.getCode() == 200) {
            String fileName = result.getResult();
                System.out.println(fileName + "fileName2");

                Image image = Image.builder()
                        .imageName(fileName)
                        .build();

                imageRepository.save(image);
        }
        return result;
    }

    @Override
    public void download(HttpServletRequest request, HttpServletResponse response, String name) {
        imageRepository.findByKey(name).ifPresent(Image -> {
            FileInfoParam param = FileInfoParam.builder().name(Image.getImageName()).build();
            System.out.println(param + "paramparamparamparamparam");
            FileService.download(request, response, ImagePath, param);
        });
    }
}