package com.enicom.board.kyoritsu.api.controller;


import com.enicom.board.kyoritsu.api.service.image.ImageService;
import com.enicom.board.kyoritsu.api.type.ResponseDataValue;
import com.enicom.board.kyoritsu.api.type.ResponseHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
@Slf4j
public class ImageController {

    private final ImageService imageService;

    @RequestMapping(path = "/image", method = {RequestMethod.POST})
    // @ApiMapping(order = 24, desc = "[자료실] 이미지
    //
    // 업로드", param = RoomInfoParam.class)
    public ResponseHandler<?> uploadRoomImage(Model model, MultipartHttpServletRequest request, @RequestPart String name, @RequestPart MultipartFile file) {
        model.addAttribute("path", request);
        System.out.println("업로드");
        return new ResponseHandler<>(imageService.upload(request, name, file));
    }

    @RequestMapping(path = "/image", method = {RequestMethod.GET})
    // @ApiMapping(order = 25, desc = "[자료실] 이미지 다운로드", param = RoomInfoParam.class)
    public void downloadRoomImage(HttpServletRequest request, HttpServletResponse response, @RequestParam(name = "name") String name) {
        System.out.println("다운로드");
        imageService.download(request, response, name);
    }
}