package com.example.sz;

import com.example.sz.Pojo.State;
import org.springframework.stereotype.Component;

@Component
public class state_info {
    public static final State 待命 = new State("待命", "/pic/state/daiming.png");
    public static final State 调动 = new State("调动", "/pic/state/diaodong.png");
    public static final State 停留 = new State("停留", "/pic/state/tingliu.png");

    public static final State 行军 = new State("行军", "/pic/state/xingjun.png");
    public static final State 占领 = new State("占领", "/pic/state/zhanling.png");
    public static final State 撤退 = new State("撤退", "/pic/state/chetui.png");
    public static final State 驻守 = new State("驻守", "/pic/state/zhushou.png");
    public static final State 战平 = new State("战平", "/pic/state/zhanping.png");
    public static final State 屯田 = new State("屯田", "/pic/state/tuntian.png");

    public static final State[] 全部状态 = new State[]{待命, 调动, 停留, 行军, 占领, 撤退, 驻守, 战平, 屯田};
    public static final State[] 停止状态 = new State[]{待命, 调动, 停留};
    public static final State[] 行动状态 = new State[]{行军, 占领, 撤退, 驻守, 战平, 屯田};
}
