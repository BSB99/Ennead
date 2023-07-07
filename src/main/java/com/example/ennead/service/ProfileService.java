//package com.example.ennead.service;
//
//import com.example.ennead.dto.ProfileRequestDto;
//import com.example.ennead.entity.User;
//import com.example.ennead.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@RequiredArgsConstructor
//@Service
//public class ProfileService {
//    private final UserRepository userRepository;
//    private  final PasswordEncoder passwordEncoder;
//
//    public boolean checkPassword(Long id, String checkPassword) {
//        User user = userRepository.findById(id).orElseThrow(() ->
//                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
//        String realPassword = user.getPassword();
//
//
//
//        boolean matches = passwordEncoder.matches(checkPassword, realPassword);
//        return matches;
//    }
//
//
//    public boolean checkNickname(ProfileRequestDto requestDto,Long id, String nickname) {
//
//
//
//
//        if(userRepository.existsByNickname(nickname)){
//            if(userRepository.findByNickname(nickname).get().getId().equals(id)  ){
//
//                return false;
//            } else{
//                return true;
//            }
//        } else{
//            return false;
//        }
//
//
//    }
//
//
//    @Transactional
//    public void update(ProfileRequestDto requestDto, User user) {
//
//        /* 회원 찾기 */
//        User user1 = userRepository.findById(requestDto.getId()).orElseThrow(() ->
//                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
//
//
//
//        /* 수정한 비밀번호 암호화 */
//        String encryptPassword = passwordEncoder.encode(requestDto.getPassword());
//        user1.Update_Profile(requestDto); // 회원 수정
//    }
//}
