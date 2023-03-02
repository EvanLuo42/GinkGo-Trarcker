package com.phakel.ginkgo.tracker.util;

import com.phakel.ginkgo.tracker.Result;
import com.phakel.ginkgo.tracker.error.Error;
import com.phakel.ginkgo.tracker.error.ErrorToHttpCode;

import javax.ws.rs.core.Response;

public class ResponseUtil {
    public static Response withResult(Result<?, ? extends Error> result) {
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
