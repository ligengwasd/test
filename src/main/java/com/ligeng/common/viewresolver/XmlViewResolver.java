package com.ligeng.common.viewresolver;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.xml.MarshallingView;

import java.util.Locale;

/**
 * Created by dev on 16-5-5.
 */
public class XmlViewResolver implements ViewResolver {

    @Override
    public View resolveViewName(String viewName, Locale locale)
            throws Exception {
        MarshallingView view = new MarshallingView();
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(DemoObj.class);
        marshaller.setPackagesToScan(new String[]{"com.ligeng.common.viewresolver"});
        view.setMarshaller(marshaller);
        return view;
    }

}
