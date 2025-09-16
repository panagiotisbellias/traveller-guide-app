#!/bin/bash
mkdir -p sbom
syft packages dir:. -o cyclonedx-json > sbom/sbom.json
syft packages dir:. -o spdx-json > sbom/sbom.spdx.json
git add sbom/
git commit -m "chore: update SBOMs" || echo "No changes to commit"
