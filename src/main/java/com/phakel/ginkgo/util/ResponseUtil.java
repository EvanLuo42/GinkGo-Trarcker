package com.phakel.ginkgo.util;

import com.phakel.ginkgo.Result;
import com.phakel.ginkgo.dto.DTO;
import com.phakel.ginkgo.error.Error;
import com.phakel.ginkgo.error.ErrorToHttpCode;

import javax.ws.rs.core.Response;

public class ResponseUtil {
    public static Response withResult(Result<? extends DTO, ? extends Error> result) {
        return result.either(
                success -> Response
                        .ok(success)
                        .build(),
                failure -> Response
                        .status(ErrorToHttpCode.from(failure).orElse(0))
                        .entity(failure)
                        .build()
        );
    }
}
