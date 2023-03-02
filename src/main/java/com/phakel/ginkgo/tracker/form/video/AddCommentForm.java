package com.phakel.ginkgo.tracker.form.video;

import com.phakel.ginkgo.tracker.form.Form;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddCommentForm extends Form {

    private String userId;

    private String text;
}
