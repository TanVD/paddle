// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: project.proto

package io.paddle.plugin.interop;

public interface PrintRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:io.paddle.plugin.interop.PrintRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string projectId = 1;</code>
   * @return The projectId.
   */
  java.lang.String getProjectId();
  /**
   * <code>string projectId = 1;</code>
   * @return The bytes for projectId.
   */
  com.google.protobuf.ByteString
      getProjectIdBytes();

  /**
   * <code>string message = 2;</code>
   * @return The message.
   */
  java.lang.String getMessage();
  /**
   * <code>string message = 2;</code>
   * @return The bytes for message.
   */
  com.google.protobuf.ByteString
      getMessageBytes();

  /**
   * <code>.io.paddle.plugin.interop.PrintRequest.Type type = 3;</code>
   * @return The enum numeric value on the wire for type.
   */
  int getTypeValue();
  /**
   * <code>.io.paddle.plugin.interop.PrintRequest.Type type = 3;</code>
   * @return The type.
   */
  io.paddle.plugin.interop.PrintRequest.Type getType();
}
