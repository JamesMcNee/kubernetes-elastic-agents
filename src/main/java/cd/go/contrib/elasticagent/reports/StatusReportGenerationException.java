/*
 * Copyright 2022 Thoughtworks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cd.go.contrib.elasticagent.reports;

import cd.go.contrib.elasticagent.model.JobIdentifier;

import static java.lang.String.format;

public class StatusReportGenerationException extends RuntimeException {
    private final String message;
    private final String detailedMessage;
    private static final String MISSING_SERVICE = "Can not find a running pod for the provided %s '%s'.";

    public StatusReportGenerationException(String message, String detailedMessage) {
        this.message = message;
        this.detailedMessage = detailedMessage;
    }

    public String getMessage() {
        return message;
    }

    public String getDetailedMessage() {
        return detailedMessage;
    }

    public static StatusReportGenerationException noRunningPod(JobIdentifier jobIdentifier) {
        return new StatusReportGenerationException("Pod is not running.", format(MISSING_SERVICE, "job identifier", jobIdentifier.getRepresentation()));
    }

    public static StatusReportGenerationException noRunningPod(String elasticAgentId) {
        return new StatusReportGenerationException("Pod is not running.", format(MISSING_SERVICE, "elastic agent id", elasticAgentId));
    }
}
