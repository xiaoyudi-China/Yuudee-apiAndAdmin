package com.dkd.common.model_custom;import com.dkd.common.constant.ResultStant;import org.junit.Test;public class ModelCustomTest {    @Test    public void  MyThreadLocalTest() throws Exception {        MyThreadLocal myThreadLocalTest = new MyThreadLocal();        MyThreadLocal.unset();        MyThreadLocal.get();        MyThreadLocal.set(new Template());    }    @Test    public void  VerbTestCustomTest() throws Exception {        VerbTestCustom verbTestCustom = new VerbTestCustom();        verbTestCustom.setIsSuccess("success");    }    @Test    public void  ResultStantTest() throws Exception {        ResultStant resultStant = new ResultStant();    }}