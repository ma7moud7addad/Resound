# Standalone repository migration

Resound was originally developed under:

`It-Works-On-My-Machine/Android/Resound/`

The application-specific history was extracted into this standalone repository
using `git filter-repo`, with the Resound directory rewritten to the repository
root.

The original monorepository remains the historical source for old cross-project
references. Because history filtering rewrites commit ancestry and paths, commit
hashes in this standalone repository may differ from hashes recorded elsewhere.

The standalone repository is now the canonical home for Resound development,
issues, releases, and updater metadata.
