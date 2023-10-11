package com.enicom.board.kyoritsu.api.controller;

import com.enicom.board.kyoritsu.api.annotation.ApiMapping;
import com.enicom.board.kyoritsu.api.service.setting.SettingService;
import com.enicom.board.kyoritsu.api.type.InfoVO;
import com.enicom.board.kyoritsu.api.type.PageVO;
import com.enicom.board.kyoritsu.api.type.ResponseHandler;
import com.enicom.board.kyoritsu.utils.ParamUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api")
public class ApiController {
    private static final Class[] classes = {
            ApiController.class,
    };

    @Value("${system.name}")
    private String name;

    @Value("${system.version}")
    private String version;

    @RequestMapping(path = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseHandler<?> getApiList() {
        List<Map<String, Object>> apiList = new ArrayList<>();
        for (@SuppressWarnings("rawtypes") Class c : classes) {
            Method[] methods = c.getDeclaredMethods();
            for (Method m : methods) {
                if (m.isAnnotationPresent(RequestMapping.class)) {
                    RequestMapping rm = m.getAnnotation(RequestMapping.class);

                    if (m.isAnnotationPresent(ApiMapping.class)) {
                        ApiMapping ma = m.getAnnotation(ApiMapping.class);

                        Map<String, Object> method = new HashMap<>();
                        method.put("path", String.join(",", rm.path()));
                        if (c.isAnnotationPresent(RequestMapping.class)) {
                            RequestMapping cm = (RequestMapping) c.getAnnotation(RequestMapping.class);
                            String _path = Arrays.stream(cm.path()).map(path -> Arrays.stream(rm.path()).map(item -> path + item).collect(Collectors.joining(","))).collect(Collectors.joining(","));
                            method.put("path", _path);
                        }

                        List<String> column = Collections.emptyList();
                        List<String> required = Collections.emptyList();
                        if (!ma.param().equals(Object.class)) {
                            List<Field> fields = new ArrayList<>();
                            if (!ma.param().getSuperclass().equals(Object.class)) {
                                fields.addAll(Arrays.asList(ma.param().getSuperclass().getDeclaredFields()));
                            }
                            fields.addAll(Arrays.asList(ma.param().getDeclaredFields()));

                            column = fields.stream().filter(field -> !field.isAnnotationPresent(NotNull.class)).map(Field::getName).collect(Collectors.toList());
                            required = fields.stream().filter(field -> field.isAnnotationPresent(NotNull.class)).map(Field::getName).collect(Collectors.toList());

                        }
                        method.put("method", rm.method());
                        method.put("class", c.getName());
                        method.put("desc", ma.desc());
                        method.put("required", required);
                        method.put("column", column);
                        method.put("RequestBody", required.size() + column.size() > 0);
                        method.put("order", ma.order());
                        method.put("body", Arrays.stream(m.getParameters()).anyMatch(p -> p.isAnnotationPresent(RequestBody.class)));

                        apiList.add(method);
                    }
                }
            }
        }

        apiList.sort((o1, o2) -> {
            int or1 = ParamUtil.parseInt(o1.get("order"));
            int or2 = ParamUtil.parseInt(o2.get("order"));
            return Integer.compare(or1, or2);
        });

        return new ResponseHandler<>(PageVO.builder(apiList).build());
    }

    @RequestMapping(path = "/info", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiMapping(order = 0, desc = "[조회] API 정보 조회")
    public ResponseHandler<?> getApiInfo() {
        Map<String, Object> result = new HashMap<>();
        result.put("CODE", "200");
        result.put("name", name);
        result.put("version", version);

        return new ResponseHandler<>(InfoVO.builder(result).build());
    }
}
