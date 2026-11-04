# Course Catalog Service

Bounded context: **Courses** — Course catalogue with credit values and modalities.

Alignment: Curriculum data; OneRoster course posture. Lifecycle transitions take no body. All data is synthetic.

| Item | Value |
|---|---|
| Port | `8084` (`HTTP_PORT`) |
| Base path | `/api/v1/courses` |
| Owns data | Course records |

Run locally: `mvn -pl services/course-catalog-service quarkus:dev`.
