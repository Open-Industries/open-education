# Standards Traceability Matrix

Every standard or framework claimed by this platform and where its evidence lives.
"Aligned to" throughout — no certification or regulatory-compliance claim is made.

| Standard / framework | Area | Evidence |
|---|---|---|
| ISO/IEC/IEEE 42010 | Architecture description | `docs/architecture/architecture-description.md`; `docs/adr/` |
| ISO/IEC 25010 | Quality model | `docs/architecture/quality-attributes.md` (QS-01…QS-08); tests; SLOs |
| ISO/IEC/IEEE 15288 / 12207 | Life-cycle processes | `docs/lifecycle/software-lifecycle.md` |
| ISO/IEC 27001 / 27002 | ISMS controls | `docs/security/` |
| Twelve-Factor App | Cloud methodology | `docs/architecture/twelve-factor.md` |
| OpenAPI 3.1 / AsyncAPI 2.6 / CloudEvents 1.0 | Contracts & events | `contracts/` |
| RFC 9457 | Error format | `Problem.java` in every service |
| IEEE 9274.1.1 (xAPI) | Learning experience records | Statement-shaped events in `contracts/asyncapi.yaml` |
| IMS LTI 1.3 | Tool interoperability / launch | Boundary: launches integrate at the gateway with the LMS |
| IMS OneRoster | Rosters and enrolments | Learner/enrolment data posture |
| Open Badges 3.0 / W3C VC | Verifiable credentials | Boundary: issuance consumes this core's completion verdicts |
| WCAG 2.2 | Accessibility | Accessible API error and content contracts |
| ISO/IEC 40180 | Quality for learning, education and training | Quality model mapping in `docs/architecture/quality-attributes.md` |
