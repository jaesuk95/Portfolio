package com.portfolio.domain.common;

import com.portfolio.domain.model.user.UserId;
import com.portfolio.utils.IpAddress;

public interface TriggeredBy {

    /**
     * 명령을 트리거한 유저의 아이디를 가져옵니다.
     *
     * @return 유저의 id
     */
    UserId getUserId();

    /**
     * 요청이 시작된 IP 주소를 가져옵니다.
     *
     * @return IP 주소
     */
    IpAddress getIpAddress();
}
