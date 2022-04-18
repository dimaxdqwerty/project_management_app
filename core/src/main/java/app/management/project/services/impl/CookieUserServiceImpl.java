package app.management.project.services.impl;

import app.management.project.services.CookieUserService;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Component(service = CookieUserService.class, immediate = true)
public class CookieUserServiceImpl implements CookieUserService {

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Reference
    private SlingRepository slingRepository;

    @Override
    public String getString() {
        return "test";
    }
}
