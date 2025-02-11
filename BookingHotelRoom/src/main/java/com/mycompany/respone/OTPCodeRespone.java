/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.respone;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author lminh
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OTPCodeRespone {
    private String otpCode;
    private LocalDateTime expiryOTPCode;
    
    
}
