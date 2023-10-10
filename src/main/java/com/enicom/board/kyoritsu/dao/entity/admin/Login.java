package com.enicom.board.kyoritsu.dao.entity.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "mn_login")
public class Login {
    @Id
    @Column(name = "ip", length = 100)
    private String ip;

    @Column(name = "manager_id", length = 20)
    private String memberId;

    @Column(name = "login_date")
    private LocalDateTime loginDate;
}
