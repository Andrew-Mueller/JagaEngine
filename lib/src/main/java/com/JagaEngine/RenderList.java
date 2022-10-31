package com.JagaEngine;

import com.JagaEngine.geometry.Vector;

import java.util.List;
import java.util.ArrayList;

public class RenderList implements IRenderItem
{
    private List<IRenderItem> model_list;

    public int getCount()
    {
        return model_list.size();
    }

    @Override
    public void Render(JagaCamera camera)
    {
        for (IRenderItem model : model_list)
        {
            model.Render(camera);
        }
    }

    public void Add(IRenderItem model)
    {
        model_list.add(model);
    }

    public void Remove(IRenderItem model)
    {
        if (model_list.contains(model))
        {
            model_list.remove(model);
        }
    }

    public void Clear()
    {
        model_list.clear();
    }

    public RenderList()
    {
        model_list = new ArrayList<IRenderItem>();
    }

    @Override
    public void MoveTo(Vector position)
    {
        for (IRenderItem model : model_list)
        {
            model.MoveTo(position);
        }
    }

    @Override
    public void MoveBy(Vector deltaPosition)
    {
        for (IRenderItem model : model_list)
        {
            model.MoveBy(deltaPosition);
        }
    }

    @Override
    public void RotateTo(Vector rotate)
    {
        for (IRenderItem model : model_list)
        {
            model.RotateTo(rotate);
        }
    }

    @Override
    public void RotateWith(float[] rotationDeltaMatrix)
    {
        for (IRenderItem model : model_list)
        {
            model.RotateWith(rotationDeltaMatrix);
        }
    }

    @Override
    public void ScaleTo(Vector scale)
    {
        for (IRenderItem model : model_list)
        {
            model.ScaleTo(scale);
        }
    }

    @Override
    public void ScaleBy(Vector deltaScale)
    {
        for (IRenderItem model : model_list)
        {
            model.ScaleBy(deltaScale);
        }
    }
}
