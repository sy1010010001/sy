package com.sy.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.UUID;

public class JwtUtil {


    public static final String SECRET = "songyan";

    /**
     * 生成token字符串的方法
     * @param id
     * @param nickname
     * @return
     */
    public static String getJwtToken(Long id,String nickname){
        String JwtToken = Jwts.builder()
                //JWT头信息
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS2256")
                //设置分类；设置过期时间 一个当前时间，一个加上设置的过期时间常量
                .setSubject("lin-user")
                .setIssuedAt(new Date())
                //设置token主体信息，存储用户信息
                .claim("id", id)
                .claim("nickname", nickname)
                .setId(UUID.randomUUID().toString())
                //.signWith(SignatureAlgorithm.ES256, SECRET)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
        return JwtToken;
    }

    /**
     * 判断token是否存在与有效
     * @Param jwtToken
     */
    public static boolean checkToken(String jwtToken){
        if (StringUtils.isEmpty(jwtToken)){
            return false;
        }
        try{
            //验证token
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(jwtToken);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

//    /**
//     * 判断token是否存在与有效
//     * @Param request
//     */
//    public static boolean checkToken(HttpServletRequest request){
//        try {
//            String token = request.getHeader("token");
//            if (StringUtils.isEmpty(token)){
//                return false;
//            }
//            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }

    /**
     * 根据token获取会员id
     * @Param request
     */
    public static Long getMemberIdByJwtToken(String token){
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        return (Long) body.get("id");
    }
}
