package org.rpc.config;

import org.springframework.stereotype.Controller;
@Controller
public interface RpcTest {

    @ReqUrl(path = "${bserver1.ip}")
    String find();
}
