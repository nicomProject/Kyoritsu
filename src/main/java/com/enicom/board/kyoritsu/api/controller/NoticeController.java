package com.enicom.board.kyoritsu.api.controller;


import com.enicom.board.kyoritsu.api.annotation.ApiMapping;
import com.enicom.board.kyoritsu.api.param.NoticeParam;
import com.enicom.board.kyoritsu.api.param.SmarteditorVO;
import com.enicom.board.kyoritsu.api.param.file.ExcelUploadParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.api.service.notice.NoticeService;
import com.enicom.board.kyoritsu.api.type.ResponseHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
@Slf4j
public class NoticeController {
    private final NoticeService noticeService;

    @RequestMapping(value = "/notice/add", method = RequestMethod.POST)
    public ResponseHandler<?> Add(@RequestBody @Valid NoticeParam param) throws Exception {
        return new ResponseHandler<>(noticeService.add(param));
    }

    @RequestMapping(path = "/notice/find", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseHandler<?> find() {
        return new ResponseHandler<>(noticeService.findAll());
    }

    @RequestMapping(path = "/notice/findSelf", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseHandler<?> findSelf(@RequestBody @Valid NoticeParam param) {
        return new ResponseHandler<>(noticeService.findAll(param));
    }

    @RequestMapping(value = "/notice/update", method = RequestMethod.POST)
    public ResponseHandler<?> update(@RequestBody @Valid NoticeParam param) throws Exception {
        return new ResponseHandler<>(noticeService.update(param));
    }

    @RequestMapping(value = "/notice/delete", method = RequestMethod.POST)
    public ResponseHandler<?> delete(@RequestBody @Valid MultipleParam param) throws Exception {
        System.out.println("hihihihi");
        return new ResponseHandler<>(noticeService.delete(param));
    }

//    @RequestMapping(value="/smarteditorMultiImageUpload")
//    public void smarteditorMultiImageUpload(HttpServletRequest request, HttpServletResponse response){
//        try {
//            //파일정보
//            String sFileInfo = "";
//            //파일명을 받는다 - 일반 원본파일명
//            String sFilename = request.getHeader("file-name");
//            //파일 확장자
//            String sFilenameExt = sFilename.substring(sFilename.lastIndexOf(".")+1);
//            //확장자를소문자로 변경
//            sFilenameExt = sFilenameExt.toLowerCase();
//
//            //이미지 검증 배열변수
//            String[] allowFileArr = {"jpg","png","bmp","gif"};
//
//            //확장자 체크
//            int nCnt = 0;
//            for(int i=0; i<allowFileArr.length; i++) {
//                if(sFilenameExt.equals(allowFileArr[i])){
//                    nCnt++;
//                }
//            }
//
//            //이미지가 아니라면
//            if(nCnt == 0) {
//                PrintWriter print = response.getWriter();
//                print.print("NOTALLOW_"+sFilename);
//                print.flush();
//                print.close();
//            } else {
//                //디렉토리 설정 및 업로드
//
//                //파일경로
//                String filePath = "src/main/resources/static/images/";
//                File file = new File(filePath);
//
//                if(!file.exists()) {
//                    file.mkdirs();
//                }
//
//                String sRealFileNm = "";
//                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
//                String today= formatter.format(new java.util.Date());
//                sRealFileNm = today+UUID.randomUUID().toString() + sFilename.substring(sFilename.lastIndexOf("."));
//                String rlFileNm = filePath + sRealFileNm;
//
//                ///////////////// 서버에 파일쓰기 /////////////////
//                InputStream inputStream = request.getInputStream();
//                OutputStream outputStream=new FileOutputStream(rlFileNm);
//                int numRead;
//                byte bytes[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
//                while((numRead = inputStream.read(bytes,0,bytes.length)) != -1){
//                    outputStream.write(bytes,0,numRead);
//                }
//                if(inputStream != null) {
//                    inputStream.close();
//                }
//                outputStream.flush();
//                outputStream.close();
//
//
//                // 정보 출력
//                sFileInfo += "&bNewLine=true";
//                System.out.println(sFileInfo + "11111");
//                // img 태그의 title 속성을 원본파일명으로 적용시켜주기 위함
//                sFileInfo += "&sFileName="+ sFilename;
//                System.out.println(sFileInfo + "222222222");
//                sFileInfo += "&sFileURL="+"/src/main/resources/static/images/"+sRealFileNm;
//                System.out.println(sFileInfo + "333333333");
//                System.out.println("&sFileURL");
//                System.out.println(filePath);
//                System.out.println(sRealFileNm);
//                PrintWriter printWriter = response.getWriter();
//                printWriter.print(sFileInfo);
//                printWriter.flush();
//                printWriter.close();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
