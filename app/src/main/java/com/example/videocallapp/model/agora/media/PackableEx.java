package com.example.videocallapp.model.agora.media;

public interface PackableEx extends Packable {
    void unmarshal(ByteBuf in);
}
