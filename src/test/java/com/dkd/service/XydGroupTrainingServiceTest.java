package com.dkd.service;import com.dkd.XiaoyudiApplication;import com.dkd.model.XydGroupTraining;import org.junit.Test;import org.junit.runner.RunWith;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;import org.springframework.boot.test.context.SpringBootTest;import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;import org.springframework.transaction.annotation.Transactional;@RunWith(SpringJUnit4ClassRunner.class)@SpringBootTest(classes = XiaoyudiApplication.class, properties = "/application.properties")@AutoConfigureMockMvc@Transactionalpublic class XydGroupTrainingServiceTest {    @Autowired    private XydGroupTrainingService xydGroupTrainingService;    @Test    public void insertSelective() {        try{            xydGroupTrainingService.insertSelective(new XydGroupTraining());        }catch (Exception e){        }    }    @Test    public void selectByPrimaryKey() {        try{            xydGroupTrainingService.selectByPrimaryKey(1);        }catch (Exception e){        }    }    @Test    public void updateByPrimaryKeySelective() {        try{            xydGroupTrainingService.updateByPrimaryKeySelective(new XydGroupTraining());        }catch (Exception e){        }    }    @Test    public void selectNearTest() {        try{            xydGroupTrainingService.selectNearTest(135);        }catch (Exception e){        }    }    @Test    public void selectnearJuziChengzu() {        try{            xydGroupTrainingService.selectnearJuziChengzu(135);        }catch (Exception e){        }    }    @Test    public void selectnearJuziFenjie() {        try{            xydGroupTrainingService.selectnearJuziFenjie(135);        }catch (Exception e){        }    }}