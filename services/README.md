# Services

One independently deployable Quarkus microservice per bounded context. Every service
follows the same hexagonal layout, exposes `/openapi`, `/q/health` and `/q/metrics`,
returns RFC 9457 problem details, and is configured only through environment variables
(Twelve-Factor). Lifecycle transitions take no body; `@Consumes` appears only on
endpoints that read one.

**Implementation order** (see `../docs/implementation-roadmap.md`):
Learners → Credit Progress → Assessment Rules → Open APIs.

| Service | Port | Alignment |
|---|---|---|
| `learner-service` | 8081 | IMS OneRoster learner posture; privacy by design |
| `progress-service` | 8082 | Program completion accounting |
| `assessment-rules-service` | 8083 | Assessment governance as auditable rules |
| `course-catalog-service` | 8084 | Curriculum data; OneRoster course posture |
| `notification-service` | 8085 | Platform support (shared pattern) |

Run the pilot service:

```bash
mvn -pl services/learner-service quarkus:dev
# OpenAPI UI: http://localhost:8081/docs — health: http://localhost:8081/q/health
```
