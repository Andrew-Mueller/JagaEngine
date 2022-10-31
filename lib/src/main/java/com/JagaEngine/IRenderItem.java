package com.JagaEngine;

import com.JagaEngine.geometry.*;

public interface IRenderItem
{
    void Render(JagaCamera camera);
    void MoveTo(Vector position);
    void MoveBy(Vector deltaPosition);

    void RotateTo(Vector rotate);
    void RotateWith(float[] rotationDeltaMatrix);

    void ScaleTo(Vector scale);
    void ScaleBy(Vector deltaScale);
}
