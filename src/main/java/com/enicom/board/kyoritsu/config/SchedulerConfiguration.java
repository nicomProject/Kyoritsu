package com.enicom.board.kyoritsu.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SchedulerConfiguration {
    //    @Scheduled(fixedDelay = 30 * 60 * 1000)
    public void run() {
        log.info("------------ Scheduler Start ------------");
        log.info("------------ Scheduler End ------------");
    }
}
