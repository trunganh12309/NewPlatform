package com.example.controller.admin;

import com.example.constant.SystemConstant;
import com.example.dto.NewDTO;
import com.example.security.utils.MessageResponseUtils;
import com.example.service.ICategoryService;
import com.example.service.INewService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
//import com.example.repository.paging.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class NewController {

    @Autowired
    private INewService newService;

    @Autowired
    private ICategoryService categories;

    @RequestMapping(value = "/admin/new/list", method = RequestMethod.GET)
    public ModelAndView showNews(@ModelAttribute(SystemConstant.MODEL) NewDTO model) {
        ModelAndView mav = new ModelAndView("admin/new/list");
        //Custom JPA
        //newService.findAll(model, new PageRequest(model.getPage(), model.getMaxPageItems()));

        //Spring data JPA
        newService.findAll(model, new PageRequest(model.getPage() - 1 , model.getMaxPageItems()));
        mav.addObject(SystemConstant.MODEL, model);
        return mav;
    }

    @RequestMapping(value = "/admin/new/edit", method = RequestMethod.GET)
    public ModelAndView editNews(@ModelAttribute(SystemConstant.MODEL) NewDTO model,
                                 @RequestParam(value = "id",required = false) Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/new/edit");
        newService.findAll(model, new PageRequest(model.getPage() - 1 , model.getMaxPageItems()));
        if(id!=null){
            model = newService.findById(id);
        }
        initMessageResponse(mav,request);
//        Map<String, String> categories = new HashMap<>();
//        categories.put("the-thao","Thể thao");
//        categories.put("chinh-tri","Chính trị");
        mav.addObject("categories", categories.getCategories());
        mav.addObject(SystemConstant.MODEL, model);
        return mav;
    }

    private void initMessageResponse(ModelAndView mav, HttpServletRequest request) {
        String message = request.getParameter("message");
        if(StringUtils.isNotBlank(message)){
            Map<String, String> messageResponse = MessageResponseUtils.getMessage(message);
            mav.addObject(SystemConstant.ALERT, messageResponse.get(SystemConstant.ALERT));
            mav.addObject(SystemConstant.MESSAGE_RESPONSE,messageResponse.get(SystemConstant.MESSAGE_RESPONSE));
        }
    }
}
