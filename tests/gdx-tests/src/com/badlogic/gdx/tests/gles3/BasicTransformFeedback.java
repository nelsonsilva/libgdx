/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.badlogic.gdx.tests.gles3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.GenericShaderProgram;
import com.badlogic.gdx.graphics.glutils.VertexBufferObjectWithVAO;
import com.badlogic.gdx.graphics.glutils.VertexData;
import com.badlogic.gdx.tests.utils.GdxTest;
import com.badlogic.gdx.utils.BufferUtils;

import java.nio.FloatBuffer;

/**
 * Basic transform feedback example based on https://open.gl/feedback
 */
public class BasicTransformFeedback extends GdxTest {

    // our input data
    static final float data[] = new float[20];
    static {
        for (int i = 0; i < 20; data[i] = i++);
    }
    GenericShaderProgram shader;

    VertexData in;

    BitmapFont font;

    SpriteBatch batch;

    StringBuilder message = new StringBuilder();

    @Override
	public void create () {

        font = new BitmapFont();
        batch = new SpriteBatch();

		String vertexShader =
			  "#version 150                 \n" +
              "in float inValue;            \n" +
              "out float outValue;          \n" +
              "void main(){                 \n" +
              "  outValue = sqrt(inValue);  \n" +
              "}";

		shader = new GenericShaderProgram();
        shader.attachShader(GL20.GL_VERTEX_SHADER, vertexShader);

        shader.captureInterleaved("outValue");
        shader.link();
        shader.begin();

        // Create VBO with VAO
        VertexAttribute inValueAttribute = new VertexAttribute(0, 1, "inValue");
        in = new VertexBufferObjectWithVAO(true, data.length, inValueAttribute);
        in.setVertices(data, 0, data.length);
        in.bind(shader);

        // Create transform feedback buffer
        FloatBuffer result = BufferUtils.newFloatBuffer(data.length);
        int out = Gdx.gl20.glGenBuffer();
        Gdx.gl20.glBindBuffer(GL20.GL_ARRAY_BUFFER, out);
        Gdx.gl20.glBufferData(GL20.GL_ARRAY_BUFFER, data.length, result, GL30.GL_STATIC_READ);

        // Perform feedback transform
        Gdx.gl20.glEnable(GL30.GL_RASTERIZER_DISCARD);

        // out.bind(shader);
        Gdx.gl30.glBindBufferBase(GL30.GL_TRANSFORM_FEEDBACK_BUFFER, 0, out);

        Gdx.gl30.glBeginTransformFeedback(GL30.GL_POINTS);

        Gdx.gl30.glDrawArrays(GL30.GL_POINTS, 0, data.length);

        Gdx.gl30.glEndTransformFeedback();

        Gdx.gl30.glFlush();

        Gdx.gl.glGetBufferSubData(GL30.GL_TRANSFORM_FEEDBACK_BUFFER, 0, result);

        shader.end();

        Gdx.gl20.glDisable(GL30.GL_RASTERIZER_DISCARD);

        println("Transform feedback for GPGPU:");
        println();
        for (int i = 0; i < result.capacity(); i++) {
            println(String.format("sqrt(%.0f): %.2f", data[i], result.get(i)));
        }
	}

    private void println() {
        println("");
    }

    private void println(String line) {
        message.append(line + "\n");
    }

	@Override
	public void render () {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        font.drawMultiLine(batch, message, 20, Gdx.graphics.getHeight() - 20);
        batch.end();
	}

    @Override
    public void dispose () {
        batch.dispose();
        font.dispose();
        shader.dispose();
        in.dispose();
    }
}
