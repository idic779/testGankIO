package com.example.administrator.testgankio.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by Administrator on 2016/6/18.
 */
@Table(database = CoderfunDb.class)
public class GirlyDbBean extends BaseModel {

    @PrimaryKey(autoincrement = true)
    int id;
    @Column
    public String who;
    @Column
    public String publishedAt;
    @Column
    public String desc;
    @Column
    public String type;
    @Column
    public String url;
    @Column
    public String used;
    @Column
    public String objectId;
    @Column
    public String createdAt;
    @Column
    public String updatedAt;

}
