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

package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;

/** <p>
 * A shader program encapsulates a vertex and fragment shader pair linked to form a shader program useable with OpenGL ES 2.0.
 * </p>
 * 
 * <p>
 * After construction a ShaderProgram can be used to draw {@link Mesh}. To make the GPU use a specific ShaderProgram the programs
 * {@link ShaderProgram#begin()} method must be used which effectively binds the program.
 * </p>
 * 
 * <p>
 * When a ShaderProgram is bound one can set uniforms, vertex attributes and attributes as needed via the respective methods.
 * </p>
 * 
 * <p>
 * A ShaderProgram can be unbound with a call to {@link ShaderProgram#end()}
 * </p>
 * 
 * <p>
 * A ShaderProgram must be disposed via a call to {@link ShaderProgram#dispose()} when it is no longer needed
 * </p>
 * 
 * <p>
 * ShaderPrograms are managed. In case the OpenGL context is lost all shaders get invalidated and have to be reloaded. This
 * happens on Android when a user switches to another application or receives an incoming call. Managed ShaderPrograms are
 * automatically reloaded when the OpenGL context is recreated so you don't have to do this manually.
 * </p>
 * 
 * @author mzechner */
public class ShaderProgram extends GenericShaderProgram {

	/** default name for position attributes **/
	public static final String POSITION_ATTRIBUTE = "a_position";
	/** default name for normal attributes **/
	public static final String NORMAL_ATTRIBUTE = "a_normal";
	/** default name for color attributes **/
	public static final String COLOR_ATTRIBUTE = "a_color";
	/** default name for texcoords attributes, append texture unit number **/
	public static final String TEXCOORD_ATTRIBUTE = "a_texCoord";
	/** default name for tangent attribute **/
	public static final String TANGENT_ATTRIBUTE = "a_tangent";
	/** default name for binormal attribute **/
	public static final String BINORMAL_ATTRIBUTE = "a_binormal";

	/** Constructs a new ShaderProgram and immediately compiles it.
	 * 
	 * @param vertexShader the vertex shader
	 * @param fragmentShader the fragment shader */

	public ShaderProgram (String vertexShader, String fragmentShader) {
        super();
		if (vertexShader == null) throw new IllegalArgumentException("vertex shader must not be null");
		if (fragmentShader == null) throw new IllegalArgumentException("fragment shader must not be null");

        attachShader(GL20.GL_VERTEX_SHADER, vertexShader);
        attachShader(GL20.GL_FRAGMENT_SHADER, fragmentShader);
		link();
	}

	public ShaderProgram (FileHandle vertexShader, FileHandle fragmentShader) {
		this(vertexShader.readString(), fragmentShader.readString());
	}

	/** @return the source of the vertex shader */
	public String getVertexShaderSource () {
        Shader shader = shaders.get(GL20.GL_VERTEX_SHADER);
        if (shader == null) {
            return null;
        }
		return shader.source;
	}

	/** @return the source of the fragment shader */
	public String getFragmentShaderSource () {
        Shader shader = shaders.get(GL20.GL_FRAGMENT_SHADER);
        if (shader == null) {
            return null;
        }
        return null;
	}
}
