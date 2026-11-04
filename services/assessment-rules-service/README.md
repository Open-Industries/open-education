# Assessment Rules Service

Bounded context: **Assessment Rules** — Decision rules binding study levels to attempt limits and integrity envelopes.

Alignment: Assessment governance as auditable rules. Decisions are `ALLOWED` / `DEGRADED` / `DENIED`, always
with the matched rule and a human-readable reason for audit. All data is synthetic.

| Item | Value |
|---|---|
| Port | `8083` (`HTTP_PORT`) |
| Base path | `/api/v1/assessment-rules` |
| Owns data | AssessmentRule rules |
| Publishes | `assessment.activated` |

Run locally: `mvn -pl services/assessment-rules-service quarkus:dev`.
