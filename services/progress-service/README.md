# Progress Service

Bounded context: **Credit Progress** — Remaining-credits accounts per learner and the credit awards against them.

Alignment: Program completion accounting. Core invariant, enforced in code and tests: **the guarded
balance/capacity never goes negative** — over-drawing operations are rejected with `422`
and nothing is recorded. All data is synthetic.

| Item | Value |
|---|---|
| Port | `8082` (`HTTP_PORT`) |
| Base path | `/api/v1/progress` |
| Owns data | CreditProgress accounts, CreditAward records |
| Publishes | `progress.consumed`, `progress.insufficient` |

Run locally: `mvn -pl services/progress-service quarkus:dev`.
