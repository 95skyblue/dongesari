package com.dongnesari.comm.util;

import java.io.BufferedReader;
import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;

// 많이 쓰는 메서드들 여기에 짱박아놓고 쓰면 될걸요
public class ServletUtil {
	// post 방식으로 보내는 json데이터 읽어서 문자열로 바꿔주는 메서드
    public static String readRequestBody(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        return sb.toString();
    }
}