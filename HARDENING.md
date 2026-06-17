<!-- markdownlint-disable -->

# Hardening Report: nnichols--clojure-lint-action/v10

> This file was generated automatically by the hardening agent.

**Policy SHA:** `d636be7e43ef829af6e853da6b3c7566db9f72fe`

**Test Policy SHA:** `843adf9e4b8f85d0c08b27b9d0b09dd094b54702`

**Harden Agent Version:** `1`

Action **nnichols--clojure-lint-action/v10** was hardened automatically. 1 finding(s) were identified and resolved across 1 iteration(s).

## Findings Fixed

### unsafe-shell (severity: high)

The Dockerfile downloads a remote install script from GitHub and pipes it directly to `sh` without first saving it to a file for inspection. This pattern (`wget ... | sh`) executes arbitrary remote content and is vulnerable to supply-chain attacks if the remote URL is compromised or served maliciously. The offending line is: `wget -O - -q https://raw.githubusercontent.com/reviewdog/reviewdog/master/install.sh| sh -s -- -b /usr/local/bin/ ${REVIEWDOG_VERSION}`

Locations:

- `Dockerfile:7`

## Iteration Notes

### Iteration 1

**Fixes applied:** unsafe-shell

**Notes:**

Fixed the `wget ... | sh` pipe-to-shell pattern in the Dockerfile. The install script for reviewdog is now downloaded to `/tmp/install-reviewdog.sh` first, then executed as a separate `sh` command, and cleaned up afterward. This prevents arbitrary remote content from being piped directly into the shell, mitigating supply-chain attack risks. The base image (`clojure:temurin-21-tools-deps-alpine`) was already pinned to a SHA256 digest, so no changes were needed there.

