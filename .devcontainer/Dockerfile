# See here for image contents: https://github.com/microsoft/vscode-dev-containers/tree/v0.140.1/containers/java/.devcontainer/base.Dockerfile

# [Choice] Java version: 11, 14
ARG VARIANT="1"
FROM dheerajdac/alpine:${VARIANT}
# [Optional] Uncomment this section to install additional OS packages.
# RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
#     && apt-get -y install --no-install-recommends <your-package-list-here>

# [Optional] Uncomment this line to install global node packages.
# RUN su vscode -c "source /usr/local/share/nvm/nvm.sh && npm install -g <your-package-here>" 2>&1

ARG KNOWN_HOSTS=.ssh
COPY $KNOWN_HOSTS /root/.ssh

COPY .gitconfig /root
RUN chmod 400 /root/.ssh/id_rsa