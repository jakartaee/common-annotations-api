#!/bin/bash -xe
#
# Copyright (c) 2024 Oracle and/or its affiliates. All rights reserved.
#
# This program and the accompanying materials are made available under the
# terms of the Eclipse Distribution License v. 1.0, which is available at
# http://www.eclipse.org/org/documents/edl-v10.php.
#
# SPDX-License-Identifier: BSD-3-Clause
#

[[ -z ${1} ]] && SUMMARY_FILE_NAME='SUMMARY.TXT' || SUMMARY_FILE_NAME=${1}


wget --no-cache -nv ${TCK_URL} -O jakarta-annotations-tck-3.0.0.zip
unzip jakarta-annotations-tck-3.0.0.zip

cd annotations-tck/artifacts

./artifact-install.sh

cd ..

mvn -Pstaging -Dglassfish.toplevel.dir=${GF_TOP_LEVEL_DIR} -Dglassfish.container.version=${GF_VERSION} verify -f tck-runner/pom.xml | tee ${WORKSPACE}/tck.log

cd ${WORKSPACE}

export NAME=${TCK_URL##*/}

echo '***********************************************************************************' >> ${WORKSPACE}/${SUMMARY_FILE_NAME}
echo '***                        TCK bundle information                               ***' >> ${WORKSPACE}/${SUMMARY_FILE_NAME}
echo "*** Name:       ${NAME}                                     ***" >> ${WORKSPACE}/${SUMMARY_FILE_NAME}
echo "*** Download URL:	${TCK_URL} ***"  >> ${WORKSPACE}/${SUMMARY_FILE_NAME}
echo '*** Date and size: '`stat -c "date: %y, size(b): %s" ${NAME}`'        ***'>> ${WORKSPACE}/${SUMMARY_FILE_NAME}
echo "*** SHA256SUM: "`sha256sum ${NAME} | awk '{print $1}'`' ***' >> ${WORKSPACE}/${SUMMARY_FILE_NAME}
echo '***                                                                             ***' >> ${WORKSPACE}/${SUMMARY_FILE_NAME}
echo '***                        MVN/JDK info                                         ***' >> ${WORKSPACE}/${SUMMARY_FILE_NAME}
mvn -v | tee -a ${WORKSPACE}/${SUMMARY_FILE_NAME} || true
echo '***********************************************************************************' >> ${WORKSPACE}/${SUMMARY_FILE_NAME}
echo '***                             TCK results summary                             ***' >> ${WORKSPACE}/${SUMMARY_FILE_NAME}
grep 'Tests run: ' ${WORKSPACE}/tck.log >> ${WORKSPACE}/${SUMMARY_FILE_NAME}

cat ${WORKSPACE}/${SUMMARY_FILE_NAME}
