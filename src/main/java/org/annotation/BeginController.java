package org.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class BeginController {
    //若只有一个bean则就自动装配这个bean；否则拿名字（beginService）去找
    @Autowired
    private BeginService beginService;


    //type则必须只有一个bean
    @Resource(type = BeginService.class)
    private BeginService beginService3;

    //若给的name不存在则报错
    @Resource (name = "beginService")
    private BeginService beginService2;

    //先byName, 再byType
    @Resource
    private BeginService beginService4;

    public void sayOK() {
        System.out.println("BeginController sayOK()");
        beginService.hi();
    }
}
