package com.portfolio.infrastructure.mail;

import com.portfolio.infrastructure.mail.EmailTemplate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mail implements Serializable {
    private static final long serialVersionUID = 9012797121754380047L;
    private String sender;
    private String receiver;
    private EmailTemplate templateId;
    private Map<String,Object> variables;

//    private Map<String,Object> test1;



}