//package com.example.ennead.jwt;
//
//import com.example.ennead.dto.ApiResponseDto;
//import com.example.ennead.dto.SigninRequestDto;
//import com.example.ennead.security.UserDetailsImpl;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import java.io.IOException;
//
//@Slf4j(topic = "로그인 및 JWT 생성")
//public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    private final JwtUtil jwtUtil;
//
//    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
//        this.jwtUtil = jwtUtil;
//        setFilterProcessesUrl("/api/auth/signin");
//    }
//
//    // 로그인 시도
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        try {
//            // json 형태의 string을 객체로 변환
//            SigninRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), SigninRequestDto.class);
//
//            // 인증 처리
//            return getAuthenticationManager().authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            requestDto.getUsername(),
//                            requestDto.getPassword(),
//                            null
//                    )
//            );
//        } catch (IOException e) {
//            log.error(e.getMessage());
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//
//    // 로그인 성공
//    // Authentication 인증 객체 받아옴 -> 그 속의 UserDetailsImpl username 가져오기
//    @Override
//    protected void successfulAuthentication(
//            HttpServletRequest request,
//            HttpServletResponse response, FilterChain chain,
//            Authentication authResult
//    ) throws IOException{
//        String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
//
//        // token 생성
//        String token = jwtUtil.createToken(username);
//        // cookie에 넣어줌
//        jwtUtil.addJwtToCookie(token, response);
//
//        ApiResponseDto apiResponseDto = new ApiResponseDto("로그인 성공", HttpStatus.OK.value());
//        String json = new ObjectMapper().writeValueAsString(apiResponseDto);
//
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().write(json);
//    }
//
//    // 로그인 실패
//    @Override
//    protected void unsuccessfulAuthentication(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            AuthenticationException failed
//    ) throws IOException{
//        response.setStatus(401); // unauthorized
//
//        ApiResponseDto apiResponseDto = new ApiResponseDto("로그인 실패", HttpStatus.UNAUTHORIZED.value());
//        String json = new ObjectMapper().writeValueAsString(apiResponseDto);
//
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().write(json);
//    }
//}
