# Open Education — Reference Learning Core Platform

A **standard, generic education core platform** that any organization in this vertical
can deploy and adapt: built as **microservices and serverless functions** on
**Java 21 + Quarkus**, aligned with **The Twelve-Factor App**, documented under the ISO
architecture and quality standards, and aligned with the domain standards of the
vertical (see the traceability matrix). Everything is *aligned to* the cited standards —
**no certification, conformance or regulatory-compliance claim is made**. All data is
**synthetic**.

> Reference implementation: small, complete and runnable. The rules that matter in this
> domain are enforced in code and covered by tests — start with the invariant:
> credits awarded to a learner never exceed the program's required total — an award that would overrun it is rejected (422) and records nothing; the remaining-credits balance never goes negative.

## Standards alignment

| Standard / framework | What it governs here | Where |
|---|---|---|
| ISO/IEC/IEEE **42010** | Architecture description | `docs/architecture/architecture-description.md` |
| ISO/IEC **25010** (SQuaRE) | Product quality model and scenarios | `docs/architecture/quality-attributes.md` |
| ISO/IEC/IEEE **15288 & 12207** | Life-cycle processes | `docs/lifecycle/software-lifecycle.md` |
| ISO/IEC **27001 & 27002** | Information security | `docs/security/` |
| **The Twelve-Factor App** | Cloud-native methodology | `docs/architecture/twelve-factor.md` |
| IEEE 9274.1.1 (xAPI) | Learning experience records | Statement-shaped events in `contracts/asyncapi.yaml` |
| IMS LTI 1.3 | Tool interoperability / launch | Boundary: launches integrate at the gateway with the LMS |
| IMS OneRoster | Rosters and enrolments | Learner/enrolment data posture |
| Open Badges 3.0 / W3C VC | Verifiable credentials | Boundary: issuance consumes this core's completion verdicts |
| WCAG 2.2 | Accessibility | Accessible API error and content contracts |
| ISO/IEC 40180 | Quality for learning, education and training | Quality model mapping in `docs/architecture/quality-attributes.md` |

Full traceability: `docs/compliance/standards-traceability.md`.

## The platform at a glance

### Microservices (Quarkus, `services/`)

| Service | Port | Alignment | Responsibility |
|---|---|---|---|
| `learner-service` | 8081 | IMS OneRoster learner posture; privacy by design | Learner registry; the institutional identifier is masked at enrolment. |
| `progress-service` | 8082 | Program completion accounting | Remaining-credits accounts per learner and the credit awards against them. |
| `assessment-rules-service` | 8083 | Assessment governance as auditable rules | Decision rules binding study levels to attempt limits and integrity envelopes. |
| `course-catalog-service` | 8084 | Curriculum data; OneRoster course posture | Course catalogue with credit values and modalities. |
| `notification-service` | 8085 | Platform support (shared pattern) | Channel-agnostic dispatch of domain notifications from events. |

### Serverless functions (Quarkus Funqy + Knative, `functions/`)

| Function | Trigger | Responsibility |
|---|---|---|
| `engagement-risk-scorer` | Event / HTTP | Scores early-warning signals (absence, inactivity, late submissions) with an auditable rationale. |
| `credential-compliance-evaluator` | Event / HTTP | Compares missing requirements and completion percentage against credential targets; one finding per breach. |

## Design principles

1. **One bounded context per service**, identical hexagonal layout everywhere.
2. **The domain invariant is code, not prose** — credits awarded to a learner never exceed the program's required total — an award that would overrun it is rejected (422) and records nothing; the remaining-credits balance never goes negative.
3. **Privacy by design** — Learner identifiers are validated at enrolment and never stored or returned in clear — only a masked form is kept. Learning records are personal data: minimized, pseudonymized by UUID in events, and all data here is synthetic.
4. **Database per service, API-first (OpenAPI), events as CloudEvents.**
5. **Adapt by configuration** — rules, thresholds, tariffs/rates and providers are data
   and configuration, not code forks (see *Adaptation guide*).

## Quick start

Prerequisites: Java 21, Maven 3.9+, optionally Docker/Podman and Knative/OpenShift.

```bash
mvn verify                                    # build and test everything
mvn -pl services/learner-service quarkus:dev
docker compose up -d                          # local PostgreSQL, Kafka, Keycloak
```

## Deployment

- **Kubernetes / OpenShift**: `kubectl apply -k deploy/kubernetes/`.
- **Serverless**: Knative Services in `deploy/knative/` (scale to zero).
- **Local**: `docker-compose.yml` for dependencies; services run with `quarkus:dev`.

## Adaptation guide

| You want to… | Change only… |
|---|---|
| Adjust domain rules and thresholds | Rule data in the `assessment-rules-service` context and function configuration |
| Change the guarded capacity semantics | The `progress-service` context (invariant stays: never negative) |
| Plug your IdP / providers | OIDC at the gateway; provider adapters at the boundary |
| Replace in-memory persistence | The repository port's JPA adapter on the service's own database (`DB_KIND`, `DB_URL`) |

## Documentation map

- Architecture (42010): `docs/architecture/` · Decisions: `docs/adr/`
- Domain standards: `docs/domain/domain-standards.md`
- Quality (25010), Security (27001/27002), Life cycle (15288/12207), API, Data, Operations: under `docs/`

## License

Apache-2.0 — see `LICENSE`.
