package general



import org.junit.*
import grails.test.mixin.*


@TestFor(ProveedorController)
@Mock(Proveedor)
class ProveedorControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/proveedor/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.proveedorInstanceList.size() == 0
        assert model.proveedorInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.proveedorInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.proveedorInstance != null
        assert view == '/proveedor/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/proveedor/show/1'
        assert controller.flash.message != null
        assert Proveedor.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/proveedor/list'


        def proveedor = new Proveedor()

        // TODO: populate domain properties

        assert proveedor.save() != null

        params.id = proveedor.id

        def model = controller.show()

        assert model.proveedorInstance == proveedor
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/proveedor/list'


        def proveedor = new Proveedor()

        // TODO: populate valid domain properties

        assert proveedor.save() != null

        params.id = proveedor.id

        def model = controller.edit()

        assert model.proveedorInstance == proveedor
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/proveedor/list'

        response.reset()


        def proveedor = new Proveedor()

        // TODO: populate valid domain properties

        assert proveedor.save() != null

        // test invalid parameters in update
        params.id = proveedor.id

        controller.update()

        assert view == "/proveedor/edit"
        assert model.proveedorInstance != null

        proveedor.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/proveedor/show/$proveedor.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/proveedor/list'

        response.reset()

        def proveedor = new Proveedor()

        // TODO: populate valid domain properties
        assert proveedor.save() != null
        assert Proveedor.count() == 1

        params.id = proveedor.id

        controller.delete()

        assert Proveedor.count() == 0
        assert Proveedor.get(proveedor.id) == null
        assert response.redirectedUrl == '/proveedor/list'


    }


}