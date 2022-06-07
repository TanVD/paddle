#!/usr/bin/env bash
import_dir='../stub/src/proto'
out_dir='.'

cd_to_plugins () {
  cd "../python-plugins"
}

activate_venv () {
    source "venv/bin/activate"
}

check_prerequisites () {
    local required_utils="grpcio grpcio-tools"

    for util in ${required_utils}
    do
        pip freeze | grep ${util} > /dev/null
        if [[ $? -eq 1 ]]; then
            echo "\"$util\" has to be installed into venv to generate python grpc stubs"
            exit 1
        fi
    done
}

generate_python_grpc () {
  python3 -m grpc_tools.protoc -I ${import_dir} --python_out=${out_dir} --grpc_python_out=${out_dir} ${import_dir}/*.proto
}

main () {
  cd_to_plugins
  activate_venv
  check_prerequisites
  generate_python_grpc
}

main "$@"

exit 0
