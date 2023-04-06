package com.portfolio.domain.model.rabbit;


public enum RabbitQueue {
    // Route Key 값들 (더 추가 가능)
    USER_REGISTER("register"),      // 회원가입
    QR_EMAIL("qrEmail"),
    ORDER("order"),
    ALIM_MESSAGE("alimMessage"),
    ALERT_KAKAO("alertKakao"),
    VERIFY_CODE("verifyCode"), CANCEL_ORDER("cancel");
    //

    private final String queueName;

    RabbitQueue(String queueName){
        this.queueName = queueName;
    }

    public String getQueueName(){
        return queueName;
    }

    // 밑에 코드는 현재 미사용
//    public static RabbitQueue find(String name){
//        for (RabbitQueue queue : RabbitQueue.values()){
//            if (queue.getQueueName().equalsIgnoreCase(name)){
//                return queue;
//            }
//        }
//        return RabbitQueue.EMPTY;
//    }
}