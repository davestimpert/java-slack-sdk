package com.slack.api.model.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ViewSubmit {
    private String type;
    private String text;
    private boolean emoji;
}
