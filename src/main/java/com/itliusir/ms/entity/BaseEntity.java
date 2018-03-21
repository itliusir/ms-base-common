package com.itliusir.ms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 基础Entity层
 *
 * @author liugang
 * @since 2018-03-07
 */
@MappedSuperclass
@Data
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fid")
    private Integer id;

    @Column(name = "flast_edit_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date lastEditTime;

    @Column(name = "fcreator_id")
    private Integer creatorId;

    @Column(name = "fcreate_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @Column(name = "flast_editor_id")
    private Integer lastEditorId;

    @Column(name = "fdeletor_id")
    private Integer deletorId;

    @Column(name = "fdelete_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date deleteTime;

    @Column(name = "fdeleted")
    private Integer deleted;

}
