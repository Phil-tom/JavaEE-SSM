package service;

import org.springframework.stereotype.Service;
import pojo.UserForm;

//注解为一个服务
@Service
public class UserServiceImpl implements UserService {
    @Override
    public boolean login(UserForm user) {
        return "zhangsan".equals(user.getUname())
                && "123456".equals(user.getUpass());
    }

    @Override
    public boolean register(UserForm user) {
        return "zhangsan".equals(user.getUname())
                && "123456".equals(user.getUpass());
    }
}

