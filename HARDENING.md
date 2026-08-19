<!-- markdownlint-disable -->

# Hardening Report: nnichols--clojure-lint-action/v10

> This file was generated automatically by the hardening agent.

**Policy SHA:** `d636be7e43ef829af6e853da6b3c7566db9f72fe`

**Test Policy SHA:** `843adf9e4b8f85d0c08b27b9d0b09dd094b54702`

**Harden Agent Version:** `2`

Action **nnichols--clojure-lint-action/v10** was hardened automatically. 1 finding(s) were identified and resolved across 1 iteration(s).

## Findings Fixed

### unsafe-shell (severity: high)

The Dockerfile pipes a remotely-fetched shell script directly to `sh` without first saving it to a file for inspection. The script is fetched from the mutable `master` branch of the reviewdog repository, so a compromised or tampered install.sh would be executed immediately inside the Docker build. Pattern matched: `wget -O - -q https://raw.githubusercontent.com/reviewdog/reviewdog/master/install.sh| sh -s -- -b /usr/local/bin/ ${REVIEWDOG_VERSION}`

Locations:

- `Dockerfile:7`

## Iteration Notes

### Iteration 1

**Fixes applied:** unsafe-shell

**Notes:**

Fixed the unsafe shell pattern in Dockerfile line 7. Changed from piping the remotely-fetched install.sh directly to sh (wget -O - ... | sh) to: (1) downloading the script to /tmp/install-reviewdog.sh first, (2) executing it as a separate step, and (3) cleaning up afterward. Also pinned the URL from the mutable 'master' branch to the specific tagged version '${REVIEWDOG_VERSION}' (v0.21.0), preventing a compromised master branch from affecting the build.

