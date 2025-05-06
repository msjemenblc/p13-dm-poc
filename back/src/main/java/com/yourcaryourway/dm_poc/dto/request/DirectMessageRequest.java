package com.yourcaryourway.dm_poc.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DirectMessageRequest {

    private Long senderId;
    private Long receiverId;
    private String content;
    
}
