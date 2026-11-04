# Learner Service

Bounded context: **Learners** — Learner registry; the institutional identifier is masked at enrolment.

Alignment: IMS OneRoster learner posture; privacy by design. Lifecycle transitions take no body; secret identifiers,
where the domain has them, are masked at creation and never stored or returned in clear.
All data is synthetic.

| Item | Value |
|---|---|
| Port | `8081` (`HTTP_PORT`) |
| Base path | `/api/v1/learners` |
| Owns data | Learner records |
| Publishes | `learner.created`, lifecycle events (see `contracts/asyncapi.yaml`) |

Run locally: `mvn -pl services/learner-service quarkus:dev`.
