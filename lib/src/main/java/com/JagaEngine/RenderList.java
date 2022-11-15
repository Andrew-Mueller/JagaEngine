package com.JagaEngine;

import com.JagaEngine.geometry.Vector;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class RenderList implements IRenderItem
{
    private List<IRenderItem> model_list;

    private ReentrantLock mutex = new ReentrantLock();

    public int getCount()
    {
        return model_list.size();
    }

    @Override
    public void Render(JagaCamera camera)
    {
        mutex.lock();

        for (IRenderItem model : model_list)
        {
            model.Render(camera);
        }

        mutex.unlock();
    }

    public void Add(IRenderItem model)
    {
        mutex.lock();

        model_list.add(model);

        mutex.unlock();
    }

    public void Remove(IRenderItem model)
    {
        mutex.lock();

        if (model_list.contains(model))
        {
            model_list.remove(model);
        }

        mutex.unlock();
    }

    public void Clear()
    {
        mutex.lock();

        model_list.clear();

        mutex.unlock();
    }

    public RenderList()
    {
        model_list = new ArrayList<IRenderItem>();
    }

    @Override
    public void MoveTo(Vector position)
    {
        mutex.lock();

        for (IRenderItem model : model_list)
        {
            model.MoveTo(position);
        }

        mutex.unlock();
    }

    @Override
    public void MoveBy(Vector deltaPosition)
    {
        mutex.lock();

        for (IRenderItem model : model_list)
        {
            model.MoveBy(deltaPosition);
        }

        mutex.unlock();
    }

    @Override
    public void RotateTo(Vector rotate)
    {
        mutex.lock();

        for (IRenderItem model : model_list)
        {
            model.RotateTo(rotate);
        }

        mutex.unlock();
    }

    @Override
    public void RotateWith(float[] rotationDeltaMatrix)
    {
        mutex.lock();

        for (IRenderItem model : model_list)
        {
            model.RotateWith(rotationDeltaMatrix);
        }

        mutex.unlock();
    }

    @Override
    public void ScaleTo(Vector scale)
    {
        mutex.lock();

        for (IRenderItem model : model_list)
        {
            model.ScaleTo(scale);
        }

        mutex.unlock();
    }

    @Override
    public void ScaleBy(Vector deltaScale)
    {
        mutex.lock();

        for (IRenderItem model : model_list)
        {
            model.ScaleBy(deltaScale);
        }

        mutex.unlock();
    }
}
