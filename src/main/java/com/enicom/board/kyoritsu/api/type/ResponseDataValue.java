package com.enicom.board.kyoritsu.api.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;

@Builder
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public final class ResponseDataValue<T> {
    @Builder.Default
    private HttpStatus status = HttpStatus.OK;

    @Builder.Default
    private T result = null;

    @Builder.Default
    private int code = 200;

    @Builder.Default
    private String desc = "성공적으로 처리되었습니다.";

    private static <T> ResponseDataValueBuilder<T> builder() {
        return (ResponseDataValueBuilder<T>) new ResponseDataValueBuilder<>();
    }

    public static <T> ResponseDataValueBuilder<T> builder(int code) {
        return (ResponseDataValueBuilder<T>) builder().code(code).desc(makeMessage(code));
    }

    public static <T> ResponseDataValueBuilder<T> builder(int code, T result) {
        return (ResponseDataValueBuilder<T>) builder().code(code).desc(makeMessage(code)).result(result);
    }

    public static String makeMessage(int code) {
        String msg = String.valueOf(code);

        if (code == 0) {
            msg = "시스템에 문제가 발생하였습니다.\r\n관리자에게 문의해 주시기 바랍니다.";
        } else if (code == 200) {
            msg = "성공적으로 처리되었습니다.";
        } else if (code == 210) {
            msg = "조회 결과가 없습니다.";
        } else if (code == 220) {
            msg = "로그인에 성공하였습니다.";
        } else if (code == 221) {
            msg = "초기 비밀번호로 로그인하였습니다.\r\n비밀번호를 변경해주십시오";

        } else if (code == 400) {
            msg = "처리 중 오류가 발생하였습니다.";
        } else if (code == 401) {
            msg = "처리중 오류가 발생하였습니다. (올바른 JSON 형식인지 확인해주세요.)";
        } else if (code == 402) {
            msg = "처리중 오류가 발생하였습니다. (올바른 파일 형식인지 확인해주세요.)";
        } else if (code == 410) {
            msg = "필수 파라미터 값이 누락되었습니다.";
        } else if (code == 420) {
            msg = "잘못된 아이디 또는 패스워드입니다.";
        } else if (code == 421) {
            msg = "로그인 세션 정보가 없습니다.";
        } else if (code == 440) {
            msg = "잘못된 데이터 유형입니다.";
        } else if (code == 441) {
            msg = "수정할 권한이 없습니다. 관리자에게 문의하십시오.";
        } else if (code == 450) {
            msg = "잘못된 파일입니다.";
        } else if (code == 480) {
            msg = "로그인에 실패하였습니다.\r\n아이디 또는 비밀번호를 확인해주세요.";
        } else if (code == 481) {
            msg = "세션이 만료되었습니다. 로그인 페이지로 이동합니다.";
        }

        return msg;
    }

    @Override
    public String toString() {
        try {
            return new JSONObject().put("code", code).put("desc", desc).toString();
        } catch (JSONException e) {
            return super.toString();
        }
    }
}